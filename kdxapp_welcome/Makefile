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

PLUGINS_DESTDIR=$(KDXPLORE_OS)/plugins

JAR_DESTDIR=$(KDXPLORE_OS)/lib

KDX_JARS=kdxos_clientlib.jar kdxos_common.jar
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

.PHONY: kdxapp_welcome.jar
kdxapp_welcome.jar:
	rm -rf $(CLASSES)
	mkdir -p $(CLASSES)
	if javac $(JAVAC_ARGS) `find $(JAVA_SRC) -name '*.java'`; \
	then \
		if test -d $(RESOURCES); then cp -r $(RESOURCES)/* $(CLASSES); else echo >&2 "?Missing $(RESOURCES)"; exit 1 ; fi; \
		jar cf $(PLUGINS_DESTDIR)/$@ -C $(CLASSES) . && echo "Finished $@"; \
	else echo javac $(JAVAC_ARGS); fi;
