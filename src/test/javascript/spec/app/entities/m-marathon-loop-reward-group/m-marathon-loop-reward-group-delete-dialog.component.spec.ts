/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonLoopRewardGroupDeleteDialogComponent } from 'app/entities/m-marathon-loop-reward-group/m-marathon-loop-reward-group-delete-dialog.component';
import { MMarathonLoopRewardGroupService } from 'app/entities/m-marathon-loop-reward-group/m-marathon-loop-reward-group.service';

describe('Component Tests', () => {
  describe('MMarathonLoopRewardGroup Management Delete Component', () => {
    let comp: MMarathonLoopRewardGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MMarathonLoopRewardGroupDeleteDialogComponent>;
    let service: MMarathonLoopRewardGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonLoopRewardGroupDeleteDialogComponent]
      })
        .overrideTemplate(MMarathonLoopRewardGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonLoopRewardGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonLoopRewardGroupService);
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
