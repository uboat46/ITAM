set cb="C:/user/Materias/SistemasDistribuidos/SD_201203_AgoDic/proysNB/tstRMI/dist/tstRMI.jar"

java -Djava.rmi.codebase=file:///%cb% -jar %cb% Master localhost reset 12
java -Djava.rmi.codebase=file:///%cb% -jar %cb% Cliente localhost
