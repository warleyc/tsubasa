/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpRankingRewardGroupDeleteDialogComponent } from 'app/entities/m-pvp-ranking-reward-group/m-pvp-ranking-reward-group-delete-dialog.component';
import { MPvpRankingRewardGroupService } from 'app/entities/m-pvp-ranking-reward-group/m-pvp-ranking-reward-group.service';

describe('Component Tests', () => {
  describe('MPvpRankingRewardGroup Management Delete Component', () => {
    let comp: MPvpRankingRewardGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MPvpRankingRewardGroupDeleteDialogComponent>;
    let service: MPvpRankingRewardGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpRankingRewardGroupDeleteDialogComponent]
      })
        .overrideTemplate(MPvpRankingRewardGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpRankingRewardGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpRankingRewardGroupService);
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
