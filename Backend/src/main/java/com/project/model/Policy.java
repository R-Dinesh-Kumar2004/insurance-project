@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDTO {
    private String name;
    private Type type;
    private double premium;
    private double coverageAmount;
    private int waitingPeriod;
    private String benefits;
    private double claimLimit;
}
