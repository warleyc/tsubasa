/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MRegulatedLeagueRankingRewardDeleteDialogComponent } from 'app/entities/m-regulated-league-ranking-reward/m-regulated-league-ranking-reward-delete-dialog.component';
import { MRegulatedLeagueRankingRewardService } from 'app/entities/m-regulated-league-ranking-reward/m-regulated-league-ranking-reward.service';

describe('Component Tests', () => {
  describe('MRegulatedLeagueRankingReward Management Delete Component', () => {
    let comp: MRegulatedLeagueRankingRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MRegulatedLeagueRankingRewardDeleteDialogComponent>;
    let service: MRegulatedLeagueRankingRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRegulatedLeagueRankingRewardDeleteDialogComponent]
      })
        .overrideTemplate(MRegulatedLeagueRankingRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MRegulatedLeagueRankingRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MRegulatedLeagueRankingRewardService);
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
