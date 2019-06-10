/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAnnounceTextUpdateComponent } from 'app/entities/m-announce-text/m-announce-text-update.component';
import { MAnnounceTextService } from 'app/entities/m-announce-text/m-announce-text.service';
import { MAnnounceText } from 'app/shared/model/m-announce-text.model';

describe('Component Tests', () => {
  describe('MAnnounceText Management Update Component', () => {
    let comp: MAnnounceTextUpdateComponent;
    let fixture: ComponentFixture<MAnnounceTextUpdateComponent>;
    let service: MAnnounceTextService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAnnounceTextUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MAnnounceTextUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MAnnounceTextUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAnnounceTextService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MAnnounceText(123);
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
        const entity = new MAnnounceText();
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
