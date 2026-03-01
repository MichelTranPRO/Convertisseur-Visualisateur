# Variables used
JC = javac
JE = java


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
all: build converter visualizer
	@echo "Compilation terminée."




# Manual compilation of the converter files
#
MAIN_CLASSES = ${CVBLD}FrequencyTable.class \
	${CVBLD}ImageReader.class \
	${CVBLD}HuffmanTree.class \
	${CVBLD}CodeTable.class \
	${CVBLD}Code.class \
	${CVBLD}ConverterController.class \
	${CVBLD}ConverterFrame.class

${CVBLD}Main.class: ${CVSRC}Main.java ${MAIN_CLASSES}
	${JC} ${JCFLAGS} $<

${CVBLD}FrequencyTable.class: ${CVSRC}FrequencyTable.java
	${JC} ${JCFLAGS} $<

${CVBLD}ImageReader.class: ${CVSRC}ImageReader.java
	${JC} ${JCFLAGS} $<

${CVBLD}HuffmanTree.class: ${CVSRC}HuffmanTree.java ${CVBLD}Node.class
	${JC} ${JCFLAGS} $<

${CVBLD}Node.class: ${CVSRC}Node.java
	${JC} ${JCFLAGS} $<

${CVBLD}CodeTable.class: ${CVSRC}CodeTable.java ${CVBLD}Node.class ${CVBLD}Code.class ${CVBLD}EntryComparator.class
	${JC} ${JCFLAGS} $<

${CVBLD}Code.class: ${CVSRC}Code.java
	${JC} ${JCFLAGS} $<

${CVBLD}EntryComparator.class: ${CVSRC}EntryComparator.java ${CVBLD}Code.class
	${JC} ${JCFLAGS} $<

${CVBLD}Writer.class: ${CVSRC}Writer.java
	${JC} ${JCFLAGS} $<

${CVBLD}WriteFileListener.class: ${CVSRC}WriteFileListener.java ${CVBLD}Writer.class
	${JC} ${JCFLAGS} $<

${CVBLD}ConverterController.class: ${CVSRC}ConverterController.java 
	${JC} ${JCFLAGS} $<

${CVBLD}ConverterFrame.class: ${CVSRC}ConverterFrame.java ${CVBLD}Writer.class
	${JC} ${JCFLAGS} $<



# Manual compilation of the visualizer files

${VZBLD}Main.class: ${VZSRC}Main.java ${VZBLD}FileTreatment.class ${VZBLD}CreateImage.class ${VZBLD}ImageVisualizer.class
	${JC} ${JCFLAGS} $<

${VZBLD}CreateImage.class: ${VZSRC}CreateImage.java
	${JC} ${JCFLAGS} $<

${VZBLD}FileTreatment.class: ${VZSRC}FileTreatment.java ${VZBLD}DataColor.class
	${JC} ${JCFLAGS} $<

${VZBLD}DataColor.class: ${VZSRC}DataColor.java
	${JC} ${JCFLAGS} $<

${VZBLD}ControllerMouseClick.class: ${VZSRC}ControllerMouseClick.java
	${JC} ${JCFLAGS} $<

${VZBLD}ControllerMouseMotion.class: ${VZSRC}ControllerMouseMotion.java ${VZBLD}ControllerMouseClick.class
	${JC} ${JCFLAGS} $<

${VZBLD}ImageVisualizer.class: ${VZSRC}ImageVisualizer.java ${VZBLD}ControllerMouseMotion.class ${VZBLD}ControllerMouseClick.class
	${JC} ${JCFLAGS} $<


# jar archives
converter: ${CVBLD}Main.class
	jar cvfe converter.jar fr.iutfbleau.pif.converter.Main -C build/ fr/iutfbleau/pif/converter

visualizer: ${VZBLD}Main.class
	jar cvfe visualizer.jar fr.iutfbleau.pif.visualizer.Main -C build/ fr/iutfbleau/pif/visualizer


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
	echo "Javadoc convertisseur : doc/converter/index.html"
	echo "Javadoc visualisateur : doc/visualizer/index.html"


# Compiled files cleaning
clean:
	rm -rf build/ converter.jar visualizer.jar doc/ *.pif
	@echo "Fichiers compilés nettoyés."
