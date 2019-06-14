/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueRankingRewardDeleteDialogComponent } from 'app/entities/m-league-ranking-reward/m-league-ranking-reward-delete-dialog.component';
import { MLeagueRankingRewardService } from 'app/entities/m-league-ranking-reward/m-league-ranking-reward.service';

describe('Component Tests', () => {
  describe('MLeagueRankingReward Management Delete Component', () => {
    let comp: MLeagueRankingRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MLeagueRankingRewardDeleteDialogComponent>;
    let service: MLeagueRankingRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueRankingRewardDeleteDialogComponent]
      })
        .overrideTemplate(MLeagueRankingRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLeagueRankingRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLeagueRankingRewardService);
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
