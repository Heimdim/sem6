package server;

import services.RemoteServiceImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
/**
 Класс, создающий сервер с использованием технологии RMI
 (<a href="https://docs.oracle.com/javase/8/docs/api/java/rmi/server/package-summary.html">Java RMI Server</a>)

 @author Dmitriy Romanovets
 @version 1.0
 */


public class Server
{
    /**Поле, хрянящее уникальную строку для клиента */
    public static final String BINDING_NAME = "sample/MyService";

    /**
     * Основной метод сервера
     *
     * @param args - параметры командной строки
     *
     * @throws RemoteException - выкидывается в случае ошибок при создании registry, stub
     * @throws AlreadyBoundException - выкидывается в случае ошибок при бинде
     */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException
    {
        System.out.print("Starting registry...");
        Registry registry= LocateRegistry.createRegistry(4996);

        RemoteServiceImpl remoteService=new RemoteServiceImpl();
        Remote stub= UnicastRemoteObject.exportObject(remoteService,0);

        System.out.print("Binding service...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        while (true)
        {
            try
            {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
