public class Socketor3 {
    public static void main(String[] args) {
        if(args.length<3){
            System.out.println("Used: \n" +
                    "java Spcketor server 8000 /\n");
            return;
        }
        Socketor3 socketor3 = new Socketor3();
        if(args[0].equals("server"))
            socketor3.runServer(args[1], args[2]);
        if(args[0].equals("client"))
            socketor3.runClient(args[1],args[2], args[3], args[4]);

    }

    private void runServer(String port, String operation) {
        Phone phone = new Phone(port);
        System.out.println("server started with operation " + operation);
        while (true){
            phone.accept();
            String a = phone.readLine();
            String b = phone.readLine();
            int result = calculate(operation, a, b);
            String message = a + " " + operation + " " + b + " = " +result;
            phone.writeLine(message);
            phone.close();

        }
    }

    private int calculate(String operation, String a, String b) {
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);

        switch (operation){
            case "*": return x*y;
            case "-": return x-y;
            case "/": return x/y;
            case "+":
            default: return x + y;
        }
    }

    private void runClient(String ip, String port, String a, String b) {
        Phone phone = new Phone(ip, port);
        phone.writeLine(a);
        phone.writeLine(b);
        String answer = phone.readLine();
        System.out.println(answer);
        phone.close();
    }
}
