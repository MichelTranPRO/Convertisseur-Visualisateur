# Variables utiliées 
JC = javac
JVM = java
SRC = src/fr/iutfbleau/papillon
JCFLAGS = -d build -classpath build -sourcepath src

# Cibles
.PHONY: all run clean 

# Cible principale 
all: build papillon.jar
	@echo "Compilation terminée."


build/VerifRappel.class: ${SRC}/VerifRappel.java
	${JC} ${JCFLAGS} $<




# Archive jar
papillon.jar: build/Main.class
	jar cvfe papillon.jar fr.iutfbleau.papillon.Main -C build . -C . res -C libs . 


# Exécution du programme
run: 
	${JVM} -jar papillon.jar

# Vérification de la création du dossier build du projet
build:
	mkdir -p build

# Création de la javadoc
javadoc:
	javadoc -d doc/ -sourcepath src fr.iutfbleau.papillon \
		-encoding UTF-8 -charset UTF-8 -windowtitle "Documentation SAE DEV 3.1"
	firefox doc/index.html

# Nettoyage des fichiers compilés
clean:
	rm -rf build/ papillon.jar doc/
	@echo "Dossier build nettoyé."
