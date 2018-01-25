public class Controller {

     Model model;
     View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void display(){
        view.printStatement(model.symbol ,model.companyName, model.price, model.percentChange, model.change);
    }
}
