/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MEmblemPartsDeleteDialogComponent } from 'app/entities/m-emblem-parts/m-emblem-parts-delete-dialog.component';
import { MEmblemPartsService } from 'app/entities/m-emblem-parts/m-emblem-parts.service';

describe('Component Tests', () => {
  describe('MEmblemParts Management Delete Component', () => {
    let comp: MEmblemPartsDeleteDialogComponent;
    let fixture: ComponentFixture<MEmblemPartsDeleteDialogComponent>;
    let service: MEmblemPartsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEmblemPartsDeleteDialogComponent]
      })
        .overrideTemplate(MEmblemPartsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEmblemPartsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEmblemPartsService);
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
