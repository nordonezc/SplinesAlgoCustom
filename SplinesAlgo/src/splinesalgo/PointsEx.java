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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.factories.ListPermutationFactory;
import org.uncommons.watchmaker.framework.factories.ObjectArrayPermutationFactory;
import org.uncommons.watchmaker.framework.factories.StringFactory;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.operators.ListCrossover;
import org.uncommons.watchmaker.framework.operators.ListOrderMutation;
import org.uncommons.watchmaker.framework.operators.ObjectArrayCrossover;
import org.uncommons.watchmaker.framework.operators.Replacement;
import org.uncommons.watchmaker.framework.operators.StringCrossover;
import org.uncommons.watchmaker.framework.operators.StringMutation;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.termination.TargetFitness;
import org.uncommons.maths.random.*;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.PopulationData;
import splinesalgo.Evaluator;


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
        /*final JFrame frame = new JFrame(PointsEx.class.getSimpleName());*/
        //frame.
          
        //frame.
              
        holder = new JPanel();
        //holder.setLayout(new BoxLayout(holder, BoxLayout.PAGE_AXIS));
        holder.setLayout(new BorderLayout(50,50));
        
        
        JButton calc =  new JButton("Calcular");
        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcFloor();
            }
        });
        buttons = new JPanel();
        
        buttons.add(calc);
        buttons.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        buttons.setSize(new Dimension(100,50));
        holder.add(buttons);
        
        
        surface = new Surface();
        //surface.setPreferredSize(new Dimension(100, 100));
        //add(surface);
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
    

    public void calcFloor(){
        FormPlate posibilities[] = new FormPlate[10];
        FormPlate p0 = new FormPlate(0,8,3,true);
        FormPlate p1 = new FormPlate(1,5,1,true);
        FormPlate p2 = new FormPlate(2,2,2,true);
        FormPlate p3 = new FormPlate(3,1,1,true);
        FormPlate p4 = new FormPlate(4,9,4,true);
        FormPlate p5 = new FormPlate(5,6,2,true);
        FormPlate p6 = new FormPlate(6,4,3,true);
        FormPlate p7 = new FormPlate(7,5,7,true);
        FormPlate p8 = new FormPlate(8,3,2,true);
        FormPlate p9 = new FormPlate(9,4,2,true);
        
        posibilities[0] = p0;
        posibilities[1] = p1;
        posibilities[2] = p2;
        posibilities[3] = p3;
        posibilities[4] = p4;
        posibilities[5] = p5;
        posibilities[6] = p6;
        posibilities[7] = p7;
        posibilities[8] = p8;
        posibilities[9] = p9;
        
        CandidateFactory<FormPlate[]> factory;
        //extend AbstractCandidateFactory 
        factory = new ObjectArrayPermutationFactory<>(posibilities);
        
        List<EvolutionaryOperator<FormPlate[]>> operators;
        operators = new LinkedList< >();
        
        operators.add(new ObjectArrayCrossover<FormPlate>());
        operators.add(new Replacement<>(factory, Probability.EVENS));
        
        EvolutionPipeline<FormPlate[]> pipeline = new EvolutionPipeline<>(operators);

        EvolutionEngine<FormPlate[]> engine;
        engine = new GenerationalEvolutionEngine<>(
                factory, pipeline, new Evaluator(), 
                new RouletteWheelSelection(),
                new MersenneTwisterRNG() );
        
        engine.addEvolutionObserver(new EvolutionObserver<FormPlate[]>(){
            @Override
            public void populationUpdate(PopulationData<? extends FormPlate[]> data){
                System.out.println("Gen " + data.getGenerationNumber() + ": " + data.getBestCandidate() + ",MF:"+ data.getBestCandidateFitness());
            }
        });
        
        FormPlate[] result = engine.evolve(5, 0, new GenerationCount(1));
        CompletePlate finalResult = new CompletePlate(result, 1);
        System.out.println("Resultado -->"+finalResult.candidateToString() + " matriz " + finalResult.toString());
        
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
