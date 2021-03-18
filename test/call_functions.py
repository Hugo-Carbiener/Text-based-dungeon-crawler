from test_vscode import tagged_words
import nltk
from nltk.corpus import wordnet




"""
déplacement:
    forward
    backward
    backtrack
    moveTo
attaque
voir équipement
utiliser objet


data:
ARMES
Pair.with("Club", 1),
Pair.with("Knife", 2),
Pair.with("Dagger", 3),
Pair.with("Bow", 4),
Pair.with("Sword", 5),
Pair.with("Axe", 6),
Pair.with("Whip", 7),
Pair.with("Claymore", 8),
Pair.with("Scythe", 9),
Pair.with("Magic AK47", 10)));

MONSTRES

Pair.with("Zombie", 1),
Pair.with("Skeleton", 2),
Pair.with("Ghost", 3),
Pair.with("Undead", 4),
Pair.with("Wolf", 5),
Pair.with("Giant spider", 6),
Pair.with("Slime", 7),
Pair.with("Ghoul", 8),
Pair.with("Necromancer", 9),
Pair.with("dragon", 10)));
"""

verb = ""
for i in tagged_words:
    print(i, i[0], i[1])
    print('V' in i[1])
    if 'V' in i[1]:
        verb = i[0]
        print(type(i[0]))
        break

print(verb)
if verb == "":
    print("Error ! Verb not found")
else:

    # verbes de référence : move, attack, use, look

    ref_vbs = ["move", "attack", "use", "look"]
    syn_tab = []

    print("----- matching with synonyms -----")
    for ref in ref_vbs:
        tmp = []
        for words in wordnet.synsets(ref):
            for lemma in words.lemmas():
                tmp.append(lemma.name())
        syn_tab.append(tmp)

    if verb in syn_tab[0]:
        print("Action : move")
    elif verb in syn_tab[1]:
        print("Action : attack")
    elif verb in syn_tab[2]:
        print("Action : use")
    elif verb in syn_tab[3]:
        print("Action : look")
    else:
        print("Error : verb unmatched")


    print("----- matching with similarity -----")

    match_tab = []
    for i in ref_vbs:
        ref = wordnet.synsets(i, "v")[0]
        word = wordnet.synsets(verb, "v")[0]
        match_tab.append((ref.wup_similarity(word), i))

    match_tab.sort(reverse=True)
    print(match_tab)
    print("Action : %s" % match_tab[0][1])


