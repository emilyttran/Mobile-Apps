public class View {

    public static void printStatement(String symbol, String companyName, double price, String percentChange, double change){
        String net = checkNet(change);

        System.out.print(symbol + "(" + companyName + ") is priced at $" + price + " and has " + net + " by " + percentChange.substring(1,percentChange.length()-1) + ".");
    }

    public static String checkNet(double change){
        String net;

        if(change > 0)
            net = "increased";
        else
            net = "decreased";

        return net;
    }
}
