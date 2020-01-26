package blackjackprototype;

import java.util.ArrayList;
import java.util.*;

public class BlackJackPrototype {

    public static void main(String[] args) {
        Scanner klaviatura = new Scanner(System.in);
        ArrayList<Integer> klientoKortos = new ArrayList<Integer>();
        ArrayList<Integer> dilerioKortos = new ArrayList<Integer>();
        ArrayList<Integer> card = new ArrayList<Integer>();
        int pinigai = 500;
        int klientoTaskai = 0;
        int dilerioTaskai = 0;
       

        for (int i = 2; i <= 11; i++) {
            if (i < 10 || i == 11) {
                for (int j = 0; j < 4; j++) {
                    card.add(i);
                }
            } else {
                for (int j = 0; j < 16; j++) {
                    card.add(i);
                }
            }
        }

        ArrayList<Integer> nkortos = new ArrayList<Integer>();
        Random random = new Random();
        int index;
        int size = card.size();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(card.size());
            nkortos.add(card.get(index));
            card.remove(index);
        }
        card = nkortos;

        while (true) {
            try {
                if (pinigai == 0) {
                    System.out.println("Jus neturite daugiau pinigu");
                    break;
                }
                System.out.println("Jus turite: " + pinigai + "€");
                System.out.println("Jusu statymas: ");
                int losejoStatymas = klaviatura.nextInt();

                if (losejoStatymas > pinigai) {
                    System.out.println("Jus neturite tiek pinigu");
                    continue;
                }

                if (losejoStatymas == 0) {
                    System.out.println("Viso geriausio!");
                    break;
                }
                
                 boolean raundoPabaiga = false;
                 boolean blackJack = false;

                for (int i = 0; i <= 1; i++) {
                    card.remove(i);
                    dilerioKortos.add(card.get(i));
                    dilerioTaskai += card.get(i);
                }

                for (int i = 0; i <= 1; i++) {
                    card.remove(i);
                    klientoKortos.add(card.get(i));
                    klientoTaskai += card.get(i);
                }

                System.out.println("Dilerio korta: " + dilerioKortos.get(0));

                if (klientoTaskai == 21) {
                    System.out.println("BlackJack");
                    blackJack = true;
                } else {
                    while (true) {
                        System.out.println("Jus turite: " + klientoTaskai + " tasku");
                        System.out.println("Duoti korta? (1-taip, 2-ne)");
                        int ats1 = 0;
                        ats1 = klaviatura.nextInt();
                        if (ats1 == 1) {

                            
                                card.remove(0);
                                klientoKortos.add(card.get(0));
                                klientoTaskai += card.get(0);
                            
                            if (klientoTaskai > 21) {
                                System.out.println("Jus pralaimejote, nes turite " + klientoTaskai + " tasku");
                                pinigai -= losejoStatymas;
                                raundoPabaiga = true;
                                break;
                            }
                            if (klientoTaskai == 21) {
                                System.out.println("BlackJack");
                                blackJack = true;
                                break;
                            }
                        }
                        if (ats1 == 2) {
                            break;
                        }
                    }
                }
                while (dilerioTaskai <= 16) {
                    for (int i = 0; i <= 1; i++) {
                        card.remove(i);
                        dilerioKortos.add(card.get(i));
                        dilerioTaskai += card.get(i);
                    }
                }

                if (raundoPabaiga == false) {
                    System.out.println("Dileris turi " + dilerioTaskai + " tasku");
                }
                if (dilerioTaskai > 21 && raundoPabaiga == false) {
                    System.out.println("Jus laimejote");
                    if (blackJack == true) {
                        pinigai += losejoStatymas * 1.5;
                    } else {
                        pinigai += losejoStatymas;
                    }
                    raundoPabaiga = true;
                }
                if (dilerioTaskai > klientoTaskai && raundoPabaiga == false) {
                    System.out.println("Jus pralaimejote");
                    pinigai -= losejoStatymas;
                    raundoPabaiga = true;
                }
                if (klientoTaskai > dilerioTaskai && raundoPabaiga == false) {
                    System.out.println("Jus laimejote");
                    if (blackJack == true) {
                        pinigai += losejoStatymas * 1.5;
                    } else {
                        pinigai += losejoStatymas;
                    }
                    raundoPabaiga = true;
                }
                if (klientoTaskai == dilerioTaskai && raundoPabaiga == false) {
                    System.out.println("Lygiosios");
                }

                while (klientoTaskai > 0) {
                    int k = klientoKortos.get(0);
                    card.add(klientoKortos.get(0));
                    klientoKortos.remove(0);
                    klientoTaskai -= k;
                    
                }
                while (dilerioTaskai > 0) {
                    int l = dilerioKortos.get(0);
                    card.add(dilerioKortos.get(0));
                    dilerioKortos.remove(0);
                    dilerioTaskai -= l;
                }
                System.out.println("");
            } catch (Exception e) {
                System.out.println("Ivyko klaida");
            }
        }

    }
}
