# Variables used
JC = javac
JE = java

COMSRC = src/fr/iutfbleau/pif/
# COMSRC = common src = src files in common between converter and visualizer

COMBLD = build/fr/iutfbleau/pif/
# COMBLD = common files build



CVSRC = src/fr/iutfbleau/pif/converter/
# CVSRC = converter src

CVBLD = build/fr/iutfbleau/pif/converter/
# CVBLD = converter build


VZSRC = src/fr/iutfbleau/pif/visualizer/
# VZSRC = visualizer src

VZBLD = build/fr/iutfbleau/pif/visualizer/
# VZBLD = visualizer build




JCFLAGS = -d build -sourcepath src



# Targets
.PHONY: all runvisu runconv clean 

# First target
all: build converter.jar #visualizer.jar
	@echo "Compilation terminée."




# Manual compilation of the common files between converter and visualizer 





# Manual compilation of the converter files

${CVBLD}Main.class: ${CVSRC}Main.java ${CVBLD}FrequencyTable.class ${CVBLD}ImageReader.class
	${JC} ${JCFLAGS} $<


${CVBLD}FrequencyTable.class: ${CVSRC}FrequencyTable.java
	${JC} ${JCFLAGS} $<

${CVBLD}ImageReader.class: ${CVSRC}ImageReader.java
	${JC} ${JCFLAGS} $<

${CVBLD}Tree.class: ${CVSRC}Tree.java ${CVBLD}Node.class
	${JC} ${JCFLAGS} $<
	
${CVBLD}Node.class: ${CVSRC}Node.java
	${JC} ${JCFLAGS} $<



# Manual compilation of the visualizer files

${VZBLD}Main.class: ${VZSRC}Main.java ${VZBLD}FileTreatment.class
	${JC} ${JCFLAGS} $<

${VZBLD}Frame.class: ${VZSRC}Frame.java
	${JC} ${JCFLAGS} $<

${VZBLD}FileTreatment.class: ${VZSRC}FileTreatment.java ${VZBLD}DataColor.class
	${JC} ${JCFLAGS} $<

${VZBLD}DataColor.class: ${VZSRC}DataColor.java
	${JC} ${JCFLAGS} $<


# jar archives
converter: ${CVBLD}Main.class
	jar cvfe converter.jar fr.iutfbleau.pif.converter.Main -C build . -C . res

visualizer: ${VZBLD}Main.class
	jar cvfe visualizer.jar fr.iutfbleau.pif.visualizer.Main -C build . -C . res 


# Programs execution
runconv: 
	${JE} -jar converter.jar ${file}

runvisu:
	${JE} -jar visualizer.jar ${file}



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
