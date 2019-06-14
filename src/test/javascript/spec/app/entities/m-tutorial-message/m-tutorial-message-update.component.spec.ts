/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTutorialMessageUpdateComponent } from 'app/entities/m-tutorial-message/m-tutorial-message-update.component';
import { MTutorialMessageService } from 'app/entities/m-tutorial-message/m-tutorial-message.service';
import { MTutorialMessage } from 'app/shared/model/m-tutorial-message.model';

describe('Component Tests', () => {
  describe('MTutorialMessage Management Update Component', () => {
    let comp: MTutorialMessageUpdateComponent;
    let fixture: ComponentFixture<MTutorialMessageUpdateComponent>;
    let service: MTutorialMessageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTutorialMessageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTutorialMessageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTutorialMessageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTutorialMessageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTutorialMessage(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTutorialMessage();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
