/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetFormationGroupDeleteDialogComponent } from 'app/entities/m-target-formation-group/m-target-formation-group-delete-dialog.component';
import { MTargetFormationGroupService } from 'app/entities/m-target-formation-group/m-target-formation-group.service';

describe('Component Tests', () => {
  describe('MTargetFormationGroup Management Delete Component', () => {
    let comp: MTargetFormationGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MTargetFormationGroupDeleteDialogComponent>;
    let service: MTargetFormationGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetFormationGroupDeleteDialogComponent]
      })
        .overrideTemplate(MTargetFormationGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetFormationGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetFormationGroupService);
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
