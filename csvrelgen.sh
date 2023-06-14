#!/bin/sh
# csvrelgen script
# 
# Maintainer: Łukasz Szeremeta
# Email: l.szeremeta.dev+mmlkg@gmail.com
# https://github.com/lszeremeta

# Check if we're inside Docker
if [ -f /.dockerenv ]; then
    APPLICATION=/app/csvrelgen.jar
    LAUNCH="java -jar -Xmx${1:-64G} $APPLICATION"
    MMLLAR=${2:-/app/input/mml.lar}
    ESXFILES=${3:-/app/input/esx_mml/}
    OUTDIR=${4:-/app/output}
else
    # Standard config if we're not inside Docker
    APPLICATION=target/csvrelgen-1.0-jar-with-dependencies.jar
    LAUNCH="java -jar -Xmx64G $APPLICATION"
    MMLLAR=/Users/artur/ESX/esx_files/mml.lar
    ESXFILES=/Users/artur/ESX/esx_files/esx_mml/
    OUTDIR=output
fi

# Create output directory if not exists
if [ ! -d $OUTDIR ]; then mkdir $OUTDIR; fi

stop() {
    if [ $? -ne 0 ]; then
        >&2 echo "Exiting due to error"
        exit 1
    fi

    echo ""
#    echo Press Enter; read xxx 
}

# CALLING

$LAUNCH labels $MMLLAR $ESXFILES $OUTDIR/local_references.csv "//Label" RELATED

stop

$LAUNCH usages $MMLLAR $ESXFILES $OUTDIR/theorem_references.csv //Theorem-Item MMLId //Theorem-Reference MMLId RELATED

stop

$LAUNCH usages $MMLLAR $ESXFILES $OUTDIR/scheme_references.csv //Scheme-Block-Item MMLId //Scheme-Justification MMLId RELATED

stop

$LAUNCH usages $MMLLAR $ESXFILES $OUTDIR/circumfix_terms.csv "//Functor-Definition/CircumfixFunctor-Pattern | //Func-Synonym/CircumfixFunctor-Pattern" absolutepatternMMLId //Circumfix-Term absolutepatternMMLId RELATED

stop

$LAUNCH usages $MMLLAR $ESXFILES $OUTDIR/infix_terms.csv "//Functor-Definition/InfixFunctor-Pattern | //Func-Synonym/InfixFunctor-Pattern" absolutepatternMMLId //Infix-Term absolutepatternMMLId RELATED

stop

$LAUNCH usages $MMLLAR $ESXFILES $OUTDIR/predicates.csv "//Predicate-Definition/Predicate-Pattern | //Pred-Synonym/Predicate-Pattern | //Pred-Antonym/Predicate-Pattern" absolutepatternMMLId //Relation-Formula absolutepatternMMLId RELATED

stop

$LAUNCH usages $MMLLAR $ESXFILES $OUTDIR/adjectives.csv "//Attribute-Definition/Attribute-Pattern | //Attr-Antonym/Attribute-Pattern | //Attr-Synonym/Attribute-Pattern" absolutepatternMMLId //Attribute absolutepatternMMLId RELATED

stop

$LAUNCH usages $MMLLAR $ESXFILES $OUTDIR/structures.csv "//Structure-Pattern" absolutepatternMMLId //Struct-Type absolutepatternMMLId RELATED

stop

$LAUNCH usages $MMLLAR $ESXFILES $OUTDIR/standard_types.csv "//Mode-Definition/Mode-Pattern" absolutepatternMMLId "//Standard-Type" absolutepatternMMLId RELATED

stop

#### Graphs

$LAUNCH graph_of_structures $MMLLAR $ESXFILES $OUTDIR/structures_graph.csv "//Structure-Definition"

stop

$LAUNCH tree_of_modes $MMLLAR $ESXFILES $OUTDIR/modes_tree.csv "//Mode-Definition | //Mode-Synonym | //Structure-Definition"

stop

#### Synonyms

$LAUNCH notations $MMLLAR $ESXFILES $OUTDIR/attrs_synonyms.csv "//Attribute-Definition/Attribute-Pattern | //Attr-Antonym/Attribute-Pattern | //Attr-Synonym/Attribute-Pattern" Attr-Synonym

stop

$LAUNCH notations $MMLLAR $ESXFILES $OUTDIR/preds_synonyms.csv "//Predicate-Definition/Predicate-Pattern | //Pred-Antonym/Predicate-Pattern | //Pred-Synonym/Predicate-Pattern" Pred-Synonym

stop

$LAUNCH notations $MMLLAR $ESXFILES $OUTDIR/modes_synonyms.csv "//Mode-Definition/Mode-Pattern | //Mode-Synonym/Mode-Pattern" Mode-Synonym

stop

$LAUNCH notations $MMLLAR $ESXFILES $OUTDIR/funcs_synonyms.csv "//Functor-Definition/InfixFunctor-Pattern | //Func-Synonym/InfixFunctor-Pattern | //Functor-Definition/CircumfixFunctor-Pattern | //Func-Synonym/CircumfixFunctor-Pattern" Func-Synonym

stop