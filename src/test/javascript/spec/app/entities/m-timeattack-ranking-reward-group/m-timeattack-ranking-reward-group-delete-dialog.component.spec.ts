/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackRankingRewardGroupDeleteDialogComponent } from 'app/entities/m-timeattack-ranking-reward-group/m-timeattack-ranking-reward-group-delete-dialog.component';
import { MTimeattackRankingRewardGroupService } from 'app/entities/m-timeattack-ranking-reward-group/m-timeattack-ranking-reward-group.service';

describe('Component Tests', () => {
  describe('MTimeattackRankingRewardGroup Management Delete Component', () => {
    let comp: MTimeattackRankingRewardGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MTimeattackRankingRewardGroupDeleteDialogComponent>;
    let service: MTimeattackRankingRewardGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackRankingRewardGroupDeleteDialogComponent]
      })
        .overrideTemplate(MTimeattackRankingRewardGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTimeattackRankingRewardGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTimeattackRankingRewardGroupService);
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
