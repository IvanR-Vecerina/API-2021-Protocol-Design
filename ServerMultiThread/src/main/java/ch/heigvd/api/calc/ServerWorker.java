package ch.heigvd.api.calc;

import javax.print.DocFlavor;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calculator worker implementation
 */
public class ServerWorker implements Runnable {
    private Socket clientSocket;
    private BufferedReader in = null;
    private BufferedWriter out = null;

    private final static Logger LOG = Logger.getLogger(ServerWorker.class.getName());

    /**
     * Instantiation of a new worker mapped to a socket
     *
     * @param clientSocket connected to worker
     */
    public ServerWorker(Socket clientSocket) throws IOException {
        // Log output on a single line
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        /* TODO: prepare everything for the ServerWorker to run when the
         *   server calls the ServerWorker.run method.
         *   Don't call the ServerWorker.run method here. It has to be called from the Server.
         */
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
    }

    /**
     * Run method of the thread.
     */
    @Override
    public void run() {

        /* TODO: implement the handling of a client connection according to the specification.
         *   The server has to do the following:
         *   - initialize the dialog according to the specification (for example send the list
         *     of possible commands)
         *   - In a loop:
         *     - Read a message from the input stream (using BufferedReader.readLine)
         *     - Handle the message
         *     - Send to result to the client
         */
        try {
            String Output = "";

            while (!clientSocket.isClosed()) {
                String line = in.readLine();
                String[] operators = line.split("\\s+"); // String array, each element is text between " "

                boolean bye = operators[0].equals("GOODBYE_MY_LOVER");

                if (!bye && operators.length !=3){
                    Output = "#ERROR - wrong syntax\n";
                }

                if (!bye && operators.length !=3){
                    Output = "#ERROR - wrong syntax\n";
                }

                int op1 = bye ? 0 : Integer.parseInt(operators[1]);
                int op2 = bye ? 0 : Integer.parseInt(operators[2]);

                switch (operators[0]){
                    case "ADD":
                        Output = op1 + " + " + op2 + " = " + (op1 + op2) + "\n";
                        break;
                    case "SUB":
                        Output = op1 + " - " + op2 + " = " + (op1 - op2) + "\n";
                        break;
                    case "MUL":
                        Output = op1 + " * " + op2 + " = " + (op1 * op2) + "\n";
                        break;
                    case "DIV":
                        Output = op1 + " / " + op2 + " = " + (op1 / op2) + "\n";
                        break;
                    case "MOD":
                        Output = op1 + " % " + op2 + " = " + (op1 % op2) + "\n";
                        break;
                    case "POW":
                        Output = op1 + "^" + op2 + " = " + Math.pow(op1,op2) + "\n";
                        break;
                    case "GOODBYE_MY_LOVER":
                        Output = "Goodbye, my friend!\n";
                        break;
                }


                out.write(Output);
                out.flush();


                if (bye){
                    in.close();
                    out.close();
                    clientSocket.close();
                }



            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}