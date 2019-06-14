/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueRankingRewardGroupDeleteDialogComponent } from 'app/entities/m-league-ranking-reward-group/m-league-ranking-reward-group-delete-dialog.component';
import { MLeagueRankingRewardGroupService } from 'app/entities/m-league-ranking-reward-group/m-league-ranking-reward-group.service';

describe('Component Tests', () => {
  describe('MLeagueRankingRewardGroup Management Delete Component', () => {
    let comp: MLeagueRankingRewardGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MLeagueRankingRewardGroupDeleteDialogComponent>;
    let service: MLeagueRankingRewardGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueRankingRewardGroupDeleteDialogComponent]
      })
        .overrideTemplate(MLeagueRankingRewardGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLeagueRankingRewardGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLeagueRankingRewardGroupService);
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
