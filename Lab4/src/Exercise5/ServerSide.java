package Exercise5;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

	public class ServerSide {

		
		public static void main(String[] args) throws Exception {
			
			
			ServerSocket serverSocket = null;
			
			try {
					    
				
				//Bind Serversocket to a port
				int portNo = 9999;
				serverSocket = new ServerSocket(portNo);
				
				System.out.println("----------------------------------------------");
				System.out.println("\t     Server Side");
				System.out.println("\t...Waiting for request...");
				System.out.println("\t      ...Running...");
				System.out.println("----------------------------------------------");
				System.out.println(" ");
				
				
				
				
				
				while(true) {
					
					//Accept client request for connection
					Socket clientSocket = serverSocket.accept();
					
					//Create stream to read data from client
					DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
					
					//create new object for translator
					Translator message = new Translator();
					
					//assign text read from client
					 message.setText(dataIn.readUTF()); 
					 
					 
					 //Object to store text
					 String text = message.getText();
					 
					 
					 //Translate the text 
					Translator textToTranslate = new Translator(text);
					
					//Create stream to write data to the network
					DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
					
					//Send data to the client
					dataOut.writeUTF(textToTranslate.getTranslatedText());
					dataOut.flush();
									
					//close the socket
					clientSocket.close();
					dataIn.close();
					dataOut.close();
				
				}
				
				
			}catch(IOException ioe) {
				
				if(serverSocket != null)
					serverSocket.close();
				
				ioe.printStackTrace();
				
			}

		}

	}
