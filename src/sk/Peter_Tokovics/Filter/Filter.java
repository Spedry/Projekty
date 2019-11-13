import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Najomnik {
    String Name;
    String TelNum;
    String Street;
    String EnterNum;
    String ApartmentNum;
    String Balance;
    boolean Borrower;

    public void setName(String name) {
        Name = name;
    } //získaj meno

    public void setTelNum(String telNum) {
        TelNum = telNum;
    } //získaj tel. čislo

    public void setStreet(String street) {
        Street = street;
    } //získaj ulicu

    public void setEnterNum(String enterNum) {
        EnterNum = enterNum;
    } //získaj čislo vchodu

    public void setApartmentNum(String apartmentNum) {
        ApartmentNum = apartmentNum;
    } //získaj čislo bytu

    public void setBalance(String balance) {
        Balance = balance;
    } //získaj stav účtu

    public void setBorrower(boolean borrower) {
        Borrower = borrower;
    } //zistí či je nájomník dlžník

    String getName() {
        return this.Name;
    } //odovzdaj meno

    String getTelNum() {
        return this.TelNum;
    } //odovzdaj tel. čislo

    String getStreet() {
        return this.Street;
    } //odovzdaj ulicu

    String getEnterNum() {
        return this.EnterNum;
    } //odovzdaj čislo vchodu

    String getApartmentNum() {
        return this.ApartmentNum;
    } //odovzdaj číslo bztu

    String getBalance() {
        return this.Balance;
    } //odovzdaj stav účtu

    boolean getBorrower() {
        return this.Borrower;
    } //povie či je Nájomník dlžník

} //Objekt Najomník (meno, tel. číslo, ulica, čislo vchodu, čislo bytu, stav účtu, dlžník/predplatitel)

public class Filter {

    static void Spacer(String Space) {
        if (Space.length() > 4) for (int i = Space.length(); i < 16; i++) System.out.print(" ");
        else for (int i = Space.length(); i < 4; i++) System.out.print(" ");
    } //odseky

    static void Print(Najomnik Najomnik) {
        System.out.print(Najomnik.getName());
        Spacer(Najomnik.getName());
        System.out.print(Najomnik.getTelNum());
        Spacer(Najomnik.getTelNum());
        System.out.print(Najomnik.getStreet());
        Spacer(Najomnik.getStreet());
        System.out.print(Najomnik.getEnterNum());
        Spacer(Najomnik.getEnterNum());
        System.out.print(Najomnik.getApartmentNum());
        Spacer(Najomnik.getApartmentNum());
        System.out.print(Najomnik.getBalance());
        Spacer(Najomnik.getBalance());
        System.out.println();
    } //print

    static boolean Borrower(int Debt) {
        if (Debt < 0) return true;
        else return false;
    }

    public static void main(String[] args) {

        //args
        String Filename = args[0];
        String Filter = args[1];

        //deffs
        Najomnik[] Najomnici = new Najomnik[30];
        String CSV = Filename + ".csv";
        //String CSV = "D:\\School\\Java\\Filter FIN\\" + Filename + ".csv";
        BufferedReader BuffRead = null;
        String TabCell = "";

        try {
            BuffRead = new BufferedReader(new FileReader(CSV)); //čitač dokumenta CVS

            for (int p = 0; (TabCell = BuffRead.readLine()) != null; p++) { //rob dokým nedojdeš na koniec

                String[] Read = TabCell.split(";"); //rozdelienie stringu do častí
                Najomnici[p] = new Najomnik(); //vytvorenie noveho najoníka

                for (int i = 0; i < Read.length; i++) {
                    switch (i) {
                        case 0: {
                            Najomnici[p].setName(Read[i]);
                            break;
                        } //meno
                        case 1: {
                            Najomnici[p].setTelNum(Read[i]);
                            break;
                        } //tel. číslo
                        case 2: {
                            Najomnici[p].setStreet(Read[i]);
                            break;
                        } //ulica
                        case 3: {
                            Najomnici[p].setEnterNum((Read[i]));
                            break;
                        } //čislo vchodu
                        case 4: {
                            Najomnici[p].setApartmentNum((Read[i]));
                            break;
                        } //číslo bytu
                        case 5: {
                            Najomnici[p].setBalance((Read[i]));
                            Najomnici[p].setBorrower(Borrower(Integer.parseInt(Najomnici[p].getBalance()))); //bool dlžník/preplatiteľ
                            break;
                        } //stav účtu
                    } //ukladanie do objektu Najomníci[]
                }
            }
            for (int p = 0;Najomnici[p] != null ; p++) {
                switch (Filter) {
                    case "d": {
                        if (Najomnici[p].getBorrower()) Print(Najomnici[p]);
                        break;
                    } //dlžník
                    case "p": {
                        if (!Najomnici[p].getBorrower()) Print(Najomnici[p]);
                        break;
                    } //predplatitel
                    case "m": {
                        if (Najomnici[p].getTelNum().charAt(0) == '0' && Najomnici[p].getTelNum().charAt(1) == '9') Print(Najomnici[p]);
                        break;
                    } // tel. čislo 09...f
                    default: {
                        Print(Najomnici[p]);
                    } //pri zle zadaní Filtra(args[1] print celú tabulku
                }
            }
        }

       catch (FileNotFoundException e) {
            e.printStackTrace();
        } //subor sa nenašial
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (BuffRead != null) { //nastala chyba
                try {
                    BuffRead.close(); //ukončiť reader
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
