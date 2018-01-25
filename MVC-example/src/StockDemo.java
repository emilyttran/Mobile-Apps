import java.util.Scanner;

public class StockDemo {

    public static Model getInfo(int i) {
        Model stock = new Model();
        stock.symbol = Constants.DATA[i][0].toString();
        stock.companyName = Constants.DATA[i][1].toString();
        stock.price = Double.parseDouble(Constants.DATA[i][2].toString());
        stock.change = Double.parseDouble(Constants.DATA[i][3].toString());
        stock.percentChange = Constants.DATA[i][4].toString();
        stock.marketCap = Constants.DATA[i][5].toString();
        return stock;
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the company symbol");
        String input = s.nextLine();
        input = input.toUpperCase();

        boolean found = false;
        int count = 0;
        while(!found && count < Constants.DATA[0].length){
            if(input.equals(Constants.DATA[count][0])){
                View view = new View();
                Controller controller = new Controller(getInfo(count),view);
                controller.display();
                found = true;
            } else if(count == Constants.DATA[0].length-1){
                System.out.println("Company symbol not found :(");
                break;
            }
            else
                count++;
        }
    }
}
