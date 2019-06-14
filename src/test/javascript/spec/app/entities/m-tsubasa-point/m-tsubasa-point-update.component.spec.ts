/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTsubasaPointUpdateComponent } from 'app/entities/m-tsubasa-point/m-tsubasa-point-update.component';
import { MTsubasaPointService } from 'app/entities/m-tsubasa-point/m-tsubasa-point.service';
import { MTsubasaPoint } from 'app/shared/model/m-tsubasa-point.model';

describe('Component Tests', () => {
  describe('MTsubasaPoint Management Update Component', () => {
    let comp: MTsubasaPointUpdateComponent;
    let fixture: ComponentFixture<MTsubasaPointUpdateComponent>;
    let service: MTsubasaPointService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTsubasaPointUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTsubasaPointUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTsubasaPointUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTsubasaPointService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTsubasaPoint(123);
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
        const entity = new MTsubasaPoint();
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
