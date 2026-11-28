package com.github.wkh131.hotelbooking.listing.model.partner;

import com.github.wkh131.hotelbooking.listing.v1.common.ModelDao;

import java.util.Optional;

/**
 * Data Access Object for {@link Partner}.
 * TODO: What happens when exceptions occur such as primary key integrity constraint,
 * transaction rollback constraint.
 * Transaction rollbacks need to be retried. Transaction rollback cant occur here because ? why?
 * is it because no READ-MODIFY-WRITE cycles in here?
 * What about idempotency keys? in case of retries that need to occur. need to know already handled?
 * What should be propagated back to the caller!!!! obviously retries should not happen here in DAO.
 * Also, the error does need to be propagated back to the caller.
 * Or freshness validation checksums (equivalent to compare-and-set?) needs to be a transaction?
 * so would it be a stored proc?
 * Compare-and-set from client side would not be good for data that has high contention since a lot
 * of failures would occur. Mainly used for update calls. would not occur for create (unless using
 * premise?) it could occur for deletes.
 * RETRY LOGIC: Client retries in fault scenarios
 *              Server retries when transaction rollback occurs. when can server vs client fix?
 *                      probably client needs to fix because premise changed?
 */
public interface PartnerDao extends ModelDao<Partner> {

    /**
     * Finds a Partner by ID.
     *
     * @param id the ID of the Partner.
     * @return an optional containing the Partner, if found, otherwise empty.
     */
    Optional<Partner> get(String id);

    /**
     * Creates a Partner.
     *
     * @param partner the Partner to create.
     * @return true if the partner didn't already exist and was created successfully, otherwise
     * false.
     */
    boolean create(Partner partner);

    /**
     * Updates a Partner.
     *
     * @param partner the value of the Partner to update to.
     * @return true if the partner already existed and was updated successfully, otherwise false.
     */
    boolean update(Partner partner);

    /**
     * Deletes a Partner.
     *
     * @param id the id of the Partner to delete.
     * @return true if the partner already existed and was deleted successfully, otherwise false.
     */
    boolean delete(String id);

}
