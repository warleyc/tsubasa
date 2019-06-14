/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackRankingRewardDeleteDialogComponent } from 'app/entities/m-timeattack-ranking-reward/m-timeattack-ranking-reward-delete-dialog.component';
import { MTimeattackRankingRewardService } from 'app/entities/m-timeattack-ranking-reward/m-timeattack-ranking-reward.service';

describe('Component Tests', () => {
  describe('MTimeattackRankingReward Management Delete Component', () => {
    let comp: MTimeattackRankingRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MTimeattackRankingRewardDeleteDialogComponent>;
    let service: MTimeattackRankingRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackRankingRewardDeleteDialogComponent]
      })
        .overrideTemplate(MTimeattackRankingRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTimeattackRankingRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTimeattackRankingRewardService);
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
