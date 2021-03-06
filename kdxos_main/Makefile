# KDXplore provides KDDart Data Exploration and Management
# Copyright (C) 2015,2016  Diversity Arrays Technology, Pty Ltd.
#
# KDXplore may be redistributed and may be modified under the terms
# of the GNU General Public License as published by the Free Software
# Foundation, either version 3 of the License, or (at your option)
# any later version.
#
# KDXplore is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with KDXplore.  If not, see <http://www.gnu.org/licenses/>.

SPACE :=
SPACE += 

LIBS_DIR=../kdxos_libs/libs
KDXPLORE_OS=../kdxplore_os

JAR_DESTDIR=$(KDXPLORE_OS)/lib

LIB_JARS=collections-generic-4.01.jar commons-logging-1.2.jar gson-2.3.1.jar \
	dalclient-core.jar \
	pearcan-core.jar\

KDX_JARS=kdxos_common.jar kdxos_clientlib.jar
KDX_JARPATH=$(subst $(SPACE),:,$(patsubst %,$(JAR_DESTDIR)/%,$(KDX_JARS)))

CLASSPATH=$(subst $(SPACE),:,$(patsubst %,$(LIBS_DIR)/%,$(LIB_JARS))):$(KDX_JARPATH)

CLASSES=./build/classes

JAVA_SRC=src/main/java
RESOURCES=src/main/resources

SRCDIRS=$(JAVA_SRC) $(RESOURCES)

ifeq ($(JAVAC_DEBUG_FLAG),)
	JAVAC_DEBUG_FLAG=-g:lines
endif
JAVAC_ARGS=$(JAVAC_DEBUG_FLAG) -d $(CLASSES) \
		-classpath $(CLASSPATH) \
		-sourcepath $(subst $(SPACE),:,$(SRCDIRS))

MF=./build/kdxplore_os.mf


.PHONY: kdxplore_os.jar
kdxplore_os.jar: compile manifest
	jar cfm $(KDXPLORE_OS)/$@ $(MF) -C $(CLASSES) .

.PHONY: manifest
manifest:
	cp $(LIBS_DIR)/*.jar $(JAR_DESTDIR)
	@echo "Main-Class: com.diversityarrays.kdxplore.KDXplore" > $(MF)
	/bin/ls -1 $(JAR_DESTDIR)/*.jar | sed -e "s,^../kdxplore_os/,," | awk -f classpath.awk >> $(MF)
	@echo "Built-By: `whoami`" >> $(MF)
	@echo "Build-Date:" `date "+%Y-%m-%d %H:%M:%S%z"` >> $(MF)
#	BUILD_DATE=`date "+%Y-%m-%d %H:%M:%S%z"`; echo "Build-Date: $$BUILD_DATE" >> $(MF)
	@echo "Manifest file is $(MF)"

.PHONY: compile
compile:
	rm -rf $(CLASSES)
	mkdir -p $(CLASSES)
	if javac $(JAVAC_ARGS) `find $(JAVA_SRC) -name '*.java'`; \
	then echo "Finished Compile for $@"; \
	if test -d $(RESOURCES); then cp -r $(RESOURCES)/* $(CLASSES); fi; \
	else echo javac $(JAVAC_ARGS); fi;
