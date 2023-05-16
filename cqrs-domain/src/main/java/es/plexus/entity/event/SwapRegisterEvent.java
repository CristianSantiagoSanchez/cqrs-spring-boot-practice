package es.plexus.entity.event;

import es.plexus.entity.swap.Swap;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SwapRegisterEvent extends BookSwapEvent{
    private Swap swap;
    private static final String SWAP_REGISTER_TOPIC = "swap_register";

    public SwapRegisterEvent(Swap swap){
        super(SWAP_REGISTER_TOPIC);
        this.swap = swap;
    }
}
