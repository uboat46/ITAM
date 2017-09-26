set cb="C:/user/Materias/SistemasDistribuidos/SD_201203_AgoDic/proysNB/tstRMI/dist/tstRMI.jar"

java -Djava.rmi.codebase=file:///%cb% -jar %cb% ServidorDeDisparo



C:\user\Materias\SistemasDistribuidos\SD_201203_AgoDic\SimpleRMI>java -Djava.rmi.codebase=file:///"C:/user/Materias/SistemasDistribuidos/SD_201203_AgoDic/proysN
B/tstRMI/build/classes/" -cp "c:\user\Materias\SistemasDistribuidos\SD_201203_AgoDic\proysNB\tstRMI\build\classes" example.hello.Distribuidor ServidorDeDisparo
Distribuidor, inciando con:
args[0]:ServidorDeDisparo
------------------------------------------
ServidorDeDisparo, inciando con:
Sin argumentos
------------------------------------------
Server exception: java.rmi.ServerException: RemoteException occurred in server thread; nested exception is:
        java.rmi.UnmarshalException: error unmarshalling arguments; nested exception is:
        java.lang.ClassNotFoundException: example.hello.IServDisparo
java.rmi.ServerException: RemoteException occurred in server thread; nested exception is:
        java.rmi.UnmarshalException: error unmarshalling arguments; nested exception is:
        java.lang.ClassNotFoundException: example.hello.IServDisparo
        at sun.rmi.server.UnicastServerRef.oldDispatch(UnicastServerRef.java:413)
        at sun.rmi.server.UnicastServerRef.dispatch(UnicastServerRef.java:267)
        at sun.rmi.transport.Transport$1.run(Transport.java:177)
        at sun.rmi.transport.Transport$1.run(Transport.java:174)


