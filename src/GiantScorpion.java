class GiantScorpion extends Challenger {
    public GiantScorpion(String name, int health, int attackPower, int armor) {
        super(name, health, attackPower, armor);
    }

    @Override
    public void specialAttack(Character you) {
        int damage = this.attackPower + 10;
        System.out.println(this.name + " uses venemous pierce and deals " + damage + " damage to your health");
        you.health -= damage;
    }
}
