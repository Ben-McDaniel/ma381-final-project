public class TypeA {
    double leaf_size;
    double height;
    double seed_density;
    int reproduction_rate;
    double disease_sus;
    int lifetime;
    double prop_repro;
    int offspring;

    int age; //cycles
    int last_repro; //# of cycles since last reproduction
    int half_age;

    public TypeA(double leaf_size, double height, double seed_density, int reproduction_rate, double disease_sus, int lifetime, double prop_repro, int offspring) {
        this.leaf_size = leaf_size;
        this.height = height;
        this.seed_density = seed_density;
        this.reproduction_rate = reproduction_rate;
        this.disease_sus = disease_sus;
        this.lifetime = lifetime;
        this.prop_repro = prop_repro;
        this.offspring = offspring;

        this.age = 0;
        this.half_age = lifetime/2;
        this.last_repro = 0;
    }

    public void timeUpdate(){
        this.age++;
        this.last_repro++;
    }


    //triange wave reproduction bonus
    public double get_reproduction_rate(){
        if(age < half_age){
            return reproduction_rate + 0.15*(age/half_age);
        }

        if(age > half_age){
            return reproduction_rate + 0.15 - 0.15*((age-half_age)/half_age);
        }

        return reproduction_rate + 0.15;
    }
}
