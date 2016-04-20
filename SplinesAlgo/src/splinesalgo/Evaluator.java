/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splinesalgo;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import java.util.List;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 *
 * @author Alfr3
 */
public class Evaluator implements FitnessEvaluator<FormPlate[]>
{
    private final int target = 0;

    /**
     * Assigns one "fitness point" for every character in the
     * candidate String that matches the corresponding position in
     * the target string.
     * @param candidate
     * @param population
     * @return matches
     */
    @Override
    public double getFitness(FormPlate[] chromosome, List<? extends FormPlate[]> population)
    {
        CompletePlate candidate = new CompletePlate(chromosome, 0);
        double matches = (int) (pow((candidate.surplus-target),2) + candidate.getSpace());
        return matches;
    }

    @Override
    public boolean isNatural()
    {
        //true -> maximizacion
        //false -> minimizacion
        return true;
    }

    
}
