package sk.Spedry.Lifo;

import java.util.Scanner;

class ZasobnikAny {
    Object stack[];
    int top;
    int len;

    ZasobnikAny(int velkost) {
        this.stack = new Object[velkost];
        this.top = 0;
        this.len = this.stack.length;
    }
}

class Zasobnik {
     int stack[];
     int top;
     int len;

     Zasobnik() {
         this.stack = new int[20];
         this.top = 0;
         this.len = this.stack.length;

         //for (int i=0; i<this.len; i++) stack[i] = -1;
     }

     void push(int Num) {
         if (isFull()) {
             this.stack[this.top] = Num;
             this.top++;
         }
         else {
             System.out.println("Zasobník je full");
         }
     }

     int pop() {
         if (!isEmpty()) {
             this.top--;
             return this.stack[top];
         } else {
             System.out.println("Zasobnik je prazdny");
             return -1;
         }
     }

     boolean isEmpty() {
         if (this.top == 0)
             return true;
         else
             return false;
     }

     boolean isFull() {
         if (this.top == this.len)
             return true;
         else
            return false;
     }
}

public class Lifo {

    public static void main(String[] args) {
        Zasobnik Ammo = new Zasobnik();
        while(true) {
            Scanner Read = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Zadaj hodnotu");
            Ammo.push(Read.nextInt());

        }
    }
}