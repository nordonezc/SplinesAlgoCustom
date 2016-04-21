/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splinesalgo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.maths.random.XORShiftRNG;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.factories.ObjectArrayPermutationFactory;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.operators.ObjectArrayCrossover;
import org.uncommons.watchmaker.framework.operators.Replacement;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.factories.ListPermutationFactory;
import org.uncommons.watchmaker.framework.operators.ListOrderCrossover;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.termination.Stagnation;

/**
 *
 * @author Alfr3
 */
public class PointsEx extends JFrame{
    Surface surface;
    JPanel holder;
    JPanel buttons;
    public PointsEx() {
        initUI();
    }

    private void initUI() {
        holder = new JPanel();
        //holder.setLayout(new BoxLayout(holder, BoxLayout.PAGE_AXIS));
        holder.setLayout(new BorderLayout(50,50));
     
        JButton calc =  new JButton("Calcular");
        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //calcFloor();
            }
        });
        
        JButton floorPlanning =  new JButton("Calcular Placa");
        floorPlanning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcFloor();
            }
        });
        
        buttons = new JPanel();
        buttons.add(floorPlanning);
        buttons.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        buttons.setSize(new Dimension(100,50));
        holder.add(buttons);
        
        surface = new Surface();
        holder.add(surface);
        add(holder);
        setTitle("Points");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);

        surface.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    
                }
                public void mouseEntered(MouseEvent evt) {
                    
                }
                public void mouseExited(MouseEvent evt) {
                    
                }
                public void mousePressed(MouseEvent evt) {
                    addPoint(evt);
                }
                public void mouseReleased(MouseEvent evt) {
                    
                }
            });
    }

    /*
    public void calcBezier(){
        char[] chars = new char[27];
        for (char c = 'A'; c <= 'Z'; c++)
        {
            chars[c - 'A'] = c;
        }
        chars[26] = ' ';
        //Factory for random 11-char Strings
        CandidateFactory<String> factory = new StringFactory(chars, 11);
        //extend AbstractCandidateFactory 
        
        List<EvolutionaryOperator<String>> operators = new LinkedList<EvolutionaryOperator<String> >();
        
        operators.add(new StringCrossover());
        operators.add(new StringMutation(chars, new Probability(0.02)));
        
        EvolutionaryOperator<String> pipeline = new EvolutionPipeline<>(operators);
        
        
        
       EvolutionEngine<String> engine = new GenerationalEvolutionEngine<>(
                //candidateFactory factory,
                //evolutionaryOperator pipeline,
                //fitnessEvaluator new Evaluator(),
                //selectionStrategy new RouletteWheelSelection(),
                //rng new MersenneTwisterRNG()
        );
     
        //
        
        //ElapsedTime
        //String result = engine.evolve(10, 0, new TargetFitness(11, true));
        //Stagnation 
        String result = engine.evolve(10, 0, new GenerationCount(10));
        
        System.out.println("Resultado -->"+result);
        
        surface.drawBezier(-269.0,19.0,384.0,375.0,312.0,-405);
        holder.repaint();
    }*/
    
    static List<FormPlate> plate;
    List<FormPlate> plateSort;

    public List<FormPlate> getPlate() {
        return plate;
    }

    public void setPlate(ArrayList<FormPlate> plate) {
        this.plate = plate;
    }
    
    public void fillPlate(Random rnd){
        List<FormPlate> plates = new LinkedList<>();
        for(int i = 0; i<10; i++)
            plates.add(new FormPlate(i,rnd.nextBoolean()));
        this.plateSort = plates;
    }
    
    public void calcFloor(){
        Random rnd = new Random();
        this.fillPlate(rnd);
        
        ListPermutationFactory<FormPlate> factory;
        factory = new ListPermutationFactory<>(plateSort);
        
        List<EvolutionaryOperator<List<FormPlate>>> operators; 
        operators = new LinkedList<EvolutionaryOperator<List<FormPlate>>>();
        
        EvolutionaryOperator<List<FormPlate>> changeOrientation;
        changeOrientation = new EvolutionaryOperator<List<FormPlate>>() {
            @Override
            public List<List<FormPlate>> apply(List<List<FormPlate>> plate, Random random) {
                Probability n = new Probability(0.3);
                if(n.nextEvent(random)){
                    LinkedList<List<FormPlate>> plateTMP = new LinkedList(plate);
                    int candidate = random.nextInt(9);
                    int candidateList = random.nextInt(plateTMP.size()-1);
                    List<FormPlate> tempP = plateTMP.remove(candidateList);
                    List<FormPlate> mutationP = mutation(tempP, candidate);
                    plateTMP.add(mutationP);
                    return plateTMP;
                }
                return plate;
            }
            public List<FormPlate> mutation(List<FormPlate> permutation,int candidate){ 
                FormPlate temp = permutation.get(candidate);
                //System.out.println("Lamina Original"+temp);
                temp.invOrientacion();
                permutation.set(candidate, temp);
                //System.out.println("Lamina modificada"+temp);
                return permutation;
            }
        };
        
        operators.add(new ListOrderCrossover());
        operators.add(changeOrientation);
        
        EvolutionaryOperator<List<FormPlate>> pipeline = new EvolutionPipeline<>(operators);

        EvolutionEngine<List<FormPlate>> engine;
        engine = new GenerationalEvolutionEngine<>(
                factory, pipeline, new Evaluator(), 
                new RouletteWheelSelection(),
                new XORShiftRNG() );

        List<FormPlate> result = engine.evolve(5, 2, new GenerationCount(2));
        CompletePlate finalResult = new CompletePlate(result);
        System.out.println("Resultado:");
        System.out.println(result);
        finalResult.out();
        
    }
    
    
    public void addPoint(MouseEvent e){
        surface.setNextPoint(new Point(e.getX(), e.getY() ));
        holder.repaint();
    }
    
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PointsEx ex = new PointsEx();
                
                ex.setVisible(true);
            }
        });
    }

    static class EvaluatorImpl extends Evaluator {

        public EvaluatorImpl() {
        }
    }

   
}
