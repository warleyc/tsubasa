/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDbMappingDetailComponent } from 'app/entities/m-db-mapping/m-db-mapping-detail.component';
import { MDbMapping } from 'app/shared/model/m-db-mapping.model';

describe('Component Tests', () => {
  describe('MDbMapping Management Detail Component', () => {
    let comp: MDbMappingDetailComponent;
    let fixture: ComponentFixture<MDbMappingDetailComponent>;
    const route = ({ data: of({ mDbMapping: new MDbMapping(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDbMappingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDbMappingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDbMappingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDbMapping).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
