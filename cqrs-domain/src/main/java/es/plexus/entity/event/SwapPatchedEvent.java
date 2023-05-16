package es.plexus.entity.event;

import es.plexus.entity.swap.Swap;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SwapPatchedEvent extends BookSwapEvent{
    private Swap swap;
    private static final String SWAP_PATCHED_TOPIC = "swap_patched";

    public SwapPatchedEvent(Swap swap){
        super(SWAP_PATCHED_TOPIC);
        this.swap = swap;
    }
}
