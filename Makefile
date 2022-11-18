# Ensimag 2A POO - TP 2018/19
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive gui_bin/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testInvader testLecture testCarte testDeplacement testPath  testElementaireMap1 testElementaireMap2 testElementaireMap3 testElementaireMap4 testEvolueMap1 testEvolueMap2 testEvolueMap3 testEvolueMap4  

#test fourni par le sujet
testInvader:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestInvader.java

testCarte:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestCarte.java

#Scenario simple d'un deplacement
testDeplacement:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestDeplacement.java

#Scenario simple d'un robot qui trouve son chemin	
testPath:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestPath.java

#Les tests de la strategie elementaire	
testElementaireMap1:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestElementaireMap1.java
testElementaireMap2:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestElementaireMap2.java
testElementaireMap3:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestElementaireMap3.java
testElementaireMap4:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestElementaireMap4.java
	
#Les tests de la strategie evolue	
testEvolueMap1:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestEvolueMap1.java
testEvolueMap2:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestEvolueMap2.java
testEvolueMap3:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestEvolueMap3.java
testEvolueMap4:
	javac -d bin -classpath gui_bin/gui.jar -sourcepath src src/tests/TestEvolueMap4.java

testLecture:
	javac -d bin -sourcepath src src/tests/TestLecteurDonnees.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin:gui_bin/gui.jar TestInvader
# ou bien lancer l'execution en passant par ce Makefile:vi
#   > make exeInvader
exeInvader: 
	java -classpath bin:gui_bin/gui.jar tests/TestInvader
	
exeCarte: 
	java -classpath bin:gui_bin/gui.jar tests/TestCarte

exeDeplacement: 
	java -classpath bin:gui_bin/gui.jar tests/TestDeplacement
	
exePath: 
	java -classpath bin:gui_bin/gui.jar tests/TestPath
	
exeElementaireMap1: 
	java -classpath bin:gui_bin/gui.jar tests/TestElementaireMap1

exeElementaireMap2: 
	java -classpath bin:gui_bin/gui.jar tests/TestElementaireMap2

exeElementaireMap3: 
	java -classpath bin:gui_bin/gui.jar tests/TestElementaireMap3

exeElementaireMap4: 
	java -classpath bin:gui_bin/gui.jar tests/TestElementaireMap4
	
exeEvolueMap1: 
	java -classpath bin:gui_bin/gui.jar tests/TestEvolueMap1

exeEvolueMap2: 
	java -classpath bin:gui_bin/gui.jar tests/TestEvolueMap2

exeEvolueMap3: 
	java -classpath bin:gui_bin/gui.jar tests/TestEvolueMap3

exeEvolueMap4: 
	java -classpath bin:gui_bin/gui.jar tests/TestEvolueMap4
	
exeLecture: 
	java -classpath bin TestLecteurDonnees cartes/carteSujet.map

clean:
	rm -rf bin/*.class
