# Variables used
JC = javac
JE = java

COMSRC = src/fr/iutfbleau/
# COMSRC = common src = files in common between converter and visualizer

CVSRC = src/fr/iutfbleau/pif/converter/
# CVSRC = converter src

CVBLD = build/fr/iutfbleau/pif/converter/
#CVBLD = converter build

VZSRC = src/fr/iutfbleau/pif/visualizer/
# VZSRC = visualiser src

VZBLD = build/fr/iutfbleau/pif/visualizer/
# VZBLD = visualizer build

JCFLAGS = -d build -sourcepath src



# Targets
.PHONY: all runvisu runconv clean 

# First target
all: build converter.jar #visualiser.jar
	@echo "Compilation terminée."

# Manual compilation of the converter files

${CVBLD}Main.class: ${CVSRC}Main.java ${CVBLD}ImageReader.class
	${JC} ${JCFLAGS} $<

${CVBLD}ImageReader.class: ${CVSRC}ImageReader.java
	${JC} ${JCFLAGS} $<



# Manual compilation of the visualizer files





# jar archives
converter: ${CVBLD}Main.class
	jar cvfe converter.jar fr.iutfbleau.pif.converter.Main -C build . -C . res

visualizer: ${VZBLD}Main.class
	jar cvfe visualizer.jar fr.iutfbleau.pif.visualizer.Main -C build . -C . res 


# Programs execution
runconv: 
	${JE} -jar converter.jar ${file}

runvisu:
	${JE} -jar visualizer.jar



# Verification of the build repository for compiled files
build:
	mkdir -p build


# javadoc creation
javadoc:
	javadoc -d doc/converter/ -sourcepath src fr.iutfbleau.pif.converter \
		-encoding UTF-8 -charset UTF-8 -windowtitle "Documentation application convertisseur SAE DEV 3.2"
	javadoc -d doc/visualizer/ -sourcepath src fr.iutfbleau.pif.visualizer \
		-encoding UTF-8 -charset UTF-8 -windowtitle "Documentation application visualisateur SAE DEV 3.2"
	echo "Test a remplir après (mettre le chemin des fichiers à exécuter pour voir la javadoc)"


# Compiled files cleaning
clean:
	rm -rf build/ converter.jar visualizer.jar doc/
	@echo "Fichiers compilés nettoyés."
