package lab6_7.eightqueen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
    public int popSize; //Kích thước quần thể
    public double mutationRate; //Tỷ lệ đột biến
    public int maxIteration; //Số đời tối đa khi thực hiện execute
    List<Node> population; //List các cá thể (quần thể)
    Random rd = new Random();

    public GeneticAlgorithm(int popSize, double mutationRate, int maxIteration){
        this.popSize = popSize;
        this.mutationRate = mutationRate;
        this.maxIteration = maxIteration;
        population = new ArrayList<>(popSize);
    }

    // Khởi tạo quần thể ban đầu F0
    public void initPopulation() {
        for (int i = 0; i < popSize; i++) {
            Node ni = new Node(true);
            population.add(ni);
        }
    }

    public Node execute() {
        //Xem xét một quần thể là đời F[i] (Ví dụ F0, F1, F2, ...)
        //F ở đây trong sinh học là viết tắt của từ Filial
        for(int filial = 0; filial < maxIteration; filial++){
            List<Node> newPopulation = new ArrayList<>(popSize);
            for(int i = 0; i < popSize; i++){
                //Bước 1: Chọn 2 cá thể
                Node x = getParentByTournamentSelection(10);
                Node y = getParentByTournamentSelection(10);

                //Bước 2: Lai 2 cá thể để tạo ra con
                Node child = reproduce(x, y);

                //Bước 3: Đột biến cá thể con theo xác suất tương ứng
                if(mutationRate > Math.random()){
                    // Đột biến (clone rất quan trọng!!!)
                    child = child.clone();
                    child.mutate();
                }

                //Bước 4: Xem xét child tốt nhất rồi thì dừng vòng lặp.
                // Nếu không tốt thì thêm con vào quần thể mới
                if(child.getH() == 0){
                    return child;
                }
                else{
                    newPopulation.add(child);
                }
            }

            //Giả sử quần thể đời cũ chết đi, khi này quần thể mới sẽ thay thế
            population = newPopulation;
        }
        return getBestCandidateInPopulation();
    }

    //Lai x và y để tạo ra con
    public Node reproduce(Node x, Node y) {
        int pivot = rd.nextInt(Node.N);
        Node child = new Node(false);
        for(int i = 0; i < pivot; i++){
            child.setQueen(i, x.getQueen(i));
        }
        for(int i = pivot; i < Node.N; i++){
            child.setQueen(i, y.getQueen(i));
        }
        return child;
    }

    // Chọn K cá thể bất kỳ trong quần thể, chọn cá thể tốt nhất trong đó
    public Node getParentByTournamentSelection(int k) {
        Node bestParent = getParentByRandomSelection();
        for(int i = 1; i < k; i++){
            Node candidate = getParentByRandomSelection();
            if(bestParent.getH() > candidate.getH()){
                bestParent = candidate;
            }
        }
        return bestParent;
    }

    //Lấy cá thể ngẫu nhiên trong quần thể
    public Node getParentByRandomSelection() {
        return population.get(rd.nextInt(popSize));
    }

    //Lấy cá thể tốt nhất trong quần thể
    public Node getBestCandidateInPopulation(){
        Node bestCandidate = population.get(0);
        for(int i = 1; i < popSize; i++){
            Node candidate = population.get(i);
            if(bestCandidate.getH() > candidate.getH()){
                bestCandidate = candidate;
            }
        }
        return bestCandidate;
    }
}
