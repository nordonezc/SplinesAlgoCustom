/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splinesalgo;

import java.util.ArrayList;
import java.util.List;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 *
 * @author Alfr3
 */
public class Evaluator implements FitnessEvaluator<List<FormPlate>>
{
    /**
     * Assigns one "fitness point" for every character in the
     * candidate String that matches the corresponding position in
     * the target string.
     * @param chromosome
     * @param population
     * @return matches
     */
    @Override
    public double getFitness(List<FormPlate> chromosome, List<? extends List<FormPlate>> population)
    {
        CompletePlate cp = new CompletePlate(chromosome);
        return cp.fitness();
    }

    @Override
    public boolean isNatural(){
        return false;
    }
}
