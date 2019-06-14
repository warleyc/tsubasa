/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetNationalityGroupDeleteDialogComponent } from 'app/entities/m-target-nationality-group/m-target-nationality-group-delete-dialog.component';
import { MTargetNationalityGroupService } from 'app/entities/m-target-nationality-group/m-target-nationality-group.service';

describe('Component Tests', () => {
  describe('MTargetNationalityGroup Management Delete Component', () => {
    let comp: MTargetNationalityGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MTargetNationalityGroupDeleteDialogComponent>;
    let service: MTargetNationalityGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetNationalityGroupDeleteDialogComponent]
      })
        .overrideTemplate(MTargetNationalityGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetNationalityGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetNationalityGroupService);
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
