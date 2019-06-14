/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTipsUpdateComponent } from 'app/entities/m-tips/m-tips-update.component';
import { MTipsService } from 'app/entities/m-tips/m-tips.service';
import { MTips } from 'app/shared/model/m-tips.model';

describe('Component Tests', () => {
  describe('MTips Management Update Component', () => {
    let comp: MTipsUpdateComponent;
    let fixture: ComponentFixture<MTipsUpdateComponent>;
    let service: MTipsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTipsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTipsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTipsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTipsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTips(123);
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
        const entity = new MTips();
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
