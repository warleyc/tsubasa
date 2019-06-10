/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDbMappingUpdateComponent } from 'app/entities/m-db-mapping/m-db-mapping-update.component';
import { MDbMappingService } from 'app/entities/m-db-mapping/m-db-mapping.service';
import { MDbMapping } from 'app/shared/model/m-db-mapping.model';

describe('Component Tests', () => {
  describe('MDbMapping Management Update Component', () => {
    let comp: MDbMappingUpdateComponent;
    let fixture: ComponentFixture<MDbMappingUpdateComponent>;
    let service: MDbMappingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDbMappingUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDbMappingUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDbMappingUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDbMappingService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDbMapping(123);
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
        const entity = new MDbMapping();
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
