/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueAffiliateRewardDeleteDialogComponent } from 'app/entities/m-league-affiliate-reward/m-league-affiliate-reward-delete-dialog.component';
import { MLeagueAffiliateRewardService } from 'app/entities/m-league-affiliate-reward/m-league-affiliate-reward.service';

describe('Component Tests', () => {
  describe('MLeagueAffiliateReward Management Delete Component', () => {
    let comp: MLeagueAffiliateRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MLeagueAffiliateRewardDeleteDialogComponent>;
    let service: MLeagueAffiliateRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueAffiliateRewardDeleteDialogComponent]
      })
        .overrideTemplate(MLeagueAffiliateRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLeagueAffiliateRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLeagueAffiliateRewardService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
