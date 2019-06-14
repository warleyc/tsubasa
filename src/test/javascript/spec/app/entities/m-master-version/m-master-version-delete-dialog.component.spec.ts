/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMasterVersionDeleteDialogComponent } from 'app/entities/m-master-version/m-master-version-delete-dialog.component';
import { MMasterVersionService } from 'app/entities/m-master-version/m-master-version.service';

describe('Component Tests', () => {
  describe('MMasterVersion Management Delete Component', () => {
    let comp: MMasterVersionDeleteDialogComponent;
    let fixture: ComponentFixture<MMasterVersionDeleteDialogComponent>;
    let service: MMasterVersionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMasterVersionDeleteDialogComponent]
      })
        .overrideTemplate(MMasterVersionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMasterVersionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMasterVersionService);
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
