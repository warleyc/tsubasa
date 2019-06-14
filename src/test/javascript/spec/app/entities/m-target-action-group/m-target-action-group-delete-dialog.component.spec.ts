/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetActionGroupDeleteDialogComponent } from 'app/entities/m-target-action-group/m-target-action-group-delete-dialog.component';
import { MTargetActionGroupService } from 'app/entities/m-target-action-group/m-target-action-group.service';

describe('Component Tests', () => {
  describe('MTargetActionGroup Management Delete Component', () => {
    let comp: MTargetActionGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MTargetActionGroupDeleteDialogComponent>;
    let service: MTargetActionGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetActionGroupDeleteDialogComponent]
      })
        .overrideTemplate(MTargetActionGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetActionGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetActionGroupService);
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
