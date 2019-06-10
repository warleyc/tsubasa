/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryFrDeleteDialogComponent } from 'app/entities/m-dictionary-fr/m-dictionary-fr-delete-dialog.component';
import { MDictionaryFrService } from 'app/entities/m-dictionary-fr/m-dictionary-fr.service';

describe('Component Tests', () => {
  describe('MDictionaryFr Management Delete Component', () => {
    let comp: MDictionaryFrDeleteDialogComponent;
    let fixture: ComponentFixture<MDictionaryFrDeleteDialogComponent>;
    let service: MDictionaryFrService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryFrDeleteDialogComponent]
      })
        .overrideTemplate(MDictionaryFrDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryFrDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryFrService);
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
