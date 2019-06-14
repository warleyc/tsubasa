/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetActionTypeGroupDeleteDialogComponent } from 'app/entities/m-target-action-type-group/m-target-action-type-group-delete-dialog.component';
import { MTargetActionTypeGroupService } from 'app/entities/m-target-action-type-group/m-target-action-type-group.service';

describe('Component Tests', () => {
  describe('MTargetActionTypeGroup Management Delete Component', () => {
    let comp: MTargetActionTypeGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MTargetActionTypeGroupDeleteDialogComponent>;
    let service: MTargetActionTypeGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetActionTypeGroupDeleteDialogComponent]
      })
        .overrideTemplate(MTargetActionTypeGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetActionTypeGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetActionTypeGroupService);
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
