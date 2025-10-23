
package application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProjetInitial {
public static void main(String[] args) {
Modifiable ent = new Modifiable (){
@Override
public int incrémenter(int val, int inc) {
return val+inc;}
@Override
public int décrémenter(int val, int inc) {
return val-inc;}
@Override
public int raz(int val) { val = 0;
return val;} };
try {
Scanner sc = new Scanner(System.in);
System.out.println("Saisir un entier:");
int valeur = sc.nextInt();
valeur = ent.incrémenter(valeur, 3);
System.out.println("valeur entier:"+valeur);
valeur = ent.décrémenter(valeur, 5);
System.out.println("valeur entier:"+valeur);
valeur=ent.raz(valeur);
System.out.println("valeur entier:"+valeur);
}
catch (InputMismatchException e){
 System.out.println("Vous n'avez pas inséré un entier ");}
} }