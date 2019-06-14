/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonRankingRewardGroupDeleteDialogComponent } from 'app/entities/m-marathon-ranking-reward-group/m-marathon-ranking-reward-group-delete-dialog.component';
import { MMarathonRankingRewardGroupService } from 'app/entities/m-marathon-ranking-reward-group/m-marathon-ranking-reward-group.service';

describe('Component Tests', () => {
  describe('MMarathonRankingRewardGroup Management Delete Component', () => {
    let comp: MMarathonRankingRewardGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MMarathonRankingRewardGroupDeleteDialogComponent>;
    let service: MMarathonRankingRewardGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonRankingRewardGroupDeleteDialogComponent]
      })
        .overrideTemplate(MMarathonRankingRewardGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonRankingRewardGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonRankingRewardGroupService);
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
